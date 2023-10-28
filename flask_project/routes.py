from flask import  flash, request, redirect, url_for
from authlib.integrations.flask_client import OAuth
from flask import render_template

from forms import RegistrationForm, LoginForm, PostForm
from flask_login import login_user, current_user, login_required, logout_user
from models import User, Post
from app import app, db, mail, login_manager
from flask_mail import Message

oauth = OAuth(app)


@login_manager.user_loader
def load_user(user_id):
    return User.query.filter(User.id == user_id).first()


def send_mail(address, Title, body):
    msg = Message(Title, sender=app.config["MAIL_USERNAME"], recipients=[address])
    msg.body = body
    mail.send(msg)


@app.route('/googlelogin')
def google():
    GOOGLE_CLIENT_ID = app.config['GOOGLE_CLIENT_ID']
    GOOGLE_CLIENT_SECRET = app.config['GOOGLE_CLIENT_SECRET']

    CONF_URL = 'https://accounts.google.com/.well-known/openid-configuration'
    oauth.register(
        name='google',
        client_id=GOOGLE_CLIENT_ID,
        client_secret=GOOGLE_CLIENT_SECRET,
        server_metadata_url=CONF_URL,
        client_kwargs={
            'scope': 'openid email profile'
        }
    )
    redirect_uri = url_for('google_auth', _external=True)
    return oauth.google.authorize_redirect(redirect_uri)


@app.route('/google/auth/', methods=['POST', 'GET'])
def google_auth():
    token = oauth.google.authorize_access_token()
    print(" Google User ", token)
    userinfo = token["userinfo"]
    social_id = userinfo["sub"]
    username = userinfo["name"]
    email = userinfo["email"]
    user = User.query.filter_by(social_id=social_id).first()
    if not user:
        user = User(social_id=social_id, email=email, username=username)
        db.session.add(user)
        db.session.commit()
        login_user(user)
        return redirect('/user/' + str(user.id))
    login_user(user)
    return redirect('/user/' + str(user.id))


# @app.route('/register', methods=['POST', 'GET'])
# def register():
#     form = RegistrationForm()
#     if form.validate_on_submit():
#         user = User(username=form.username.data, email=form.email.data)
#         user.set_password(form.password1.data)
#         db.session.add(user)
#         db.session.commit()
#         return redirect(url_for('login'))
#     return render_template('register.html', form=form)
#
#
# @app.route('/login', methods=['GET', 'POST'])
# def login():
#     form = LoginForm()
#     if form.validate_on_submit():
#         user = User.query.filter_by(email=form.email.data).first()
#         if user is not None and user.check_password(form.password.data):
#             login_user(user)
#             next = request.args.get("next")
#             return redirect('/user/' + str(user.id))
#         flash('Invalid email address or Password.')
#     return render_template('login.html', form=form)
# #

@app.route("/logout")
@login_required
def logout():
    logout_user()
    return redirect("/MainPage")


@app.route('/post', methods=['GET', 'POST'])
def post():
    form = PostForm()
    if form.validate_on_submit():
        post = Post(Title=form.Title.data, Content=form.Contents.data, poster=current_user)
        db.session.add(post)
        db.session.commit()
        return redirect("/MainPage")
    return render_template('post.html', form=form);


@app.route('/mail/<int:post_id>"')
def mail1(post_id):
    post_ = Post.query.get_or_404(post_id)
    send_mail(current_user.email, post_.Title, post_.Content)
    return redirect(request.referrer)


@app.route('/user/<id>')
def profile(id):
    user = User.query.filter_by(id=id).first_or_404()
    # posts = Post.query.filter_by(poster_name=id)
    total_likes = 0
    for post_ in user.posts:
        a = len(post_.liked_by),
        total_likes += a[0]
    return render_template('user_view.html', this_user=user, total_likes=total_likes)


@app.route('/MainPage')
def MainPage():
    user = current_user
    posts = Post.query.all()
    return render_template('MainPage.html', this_user=user, posts=posts)


@app.route("/like/<int:post_id>")
@login_required
def like(post_id):
    post_ = Post.query.get_or_404(post_id)
    current_user.like_post(post_)
    db.session.commit()
    return redirect(request.referrer)


@app.route("/unlike/<int:post_id>")
@login_required
def unlike(post_id):
    post_ = Post.query.get_or_404(post_id)
    current_user.unlike_post(post_)
    db.session.commit()
    return redirect(request.referrer)
