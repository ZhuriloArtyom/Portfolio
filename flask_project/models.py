from app import db, login_manager
from hashlib import md5
from flask_login import UserMixin
from datetime import datetime
from sqlalchemy import delete
from werkzeug.security import generate_password_hash, check_password_hash


user_post = db.Table("user_post",
                      db.Column("user_id", db.Integer, db.ForeignKey("user.id")),
                      db.Column("post_id", db.Integer, db.ForeignKey("post.id")))


class User(UserMixin, db.Model):
    id = db.Column(db.Integer, primary_key=True)
    social_id = db.Column(db.String(64), nullable=False, unique=True)
    username = db.Column(db.String(50), index=True, unique=True)
    email = db.Column(db.String(150), unique=True, index=True)
    password_hash = db.Column(db.String(150))
    joined_at = db.Column(db.DateTime(), default=datetime.utcnow, index=True)
    is_authenticated = True

    def set_password(self, password):
        self.password_hash = generate_password_hash(password)

    def check_password(self, password):
        return check_password_hash(self.password_hash, password)

    def like_post(self, post):
        if not self.has_liked_post(post):
            post.liked_by.append(self)
            db.session.add(post)
            db.session.commit()

    def unlike_post(self, post):
        stmt = delete(user_post).where(user_post.c.user_id == self.id).where( user_post.c.post_id == post.id)
        db.session.execute(stmt)


    def has_liked_post(self, post):
        return self in post.liked_by

    @login_manager.user_loader
    def load_user(self):
        return User.id

    def avatar(self, size):
        digest = md5(self.email.lower().encode('utf-8')).hexdigest()
        return 'https://www.gravatar.com/avatar/{}?d=identicon&s={}'.format(
            digest, size)


class Post(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    Title = db.Column(db.String(128))
    Content = db.Column(db.String(1000))
    poster = db.relationship("User", backref='posts')
    poster_name = db.Column(db.Integer, db.ForeignKey('user.id'))
    liked_by = db.relationship("User", secondary=user_post, backref = 'liked_posts')
    time = db.Column(db.DateTime(), default=datetime.utcnow, index=True)

    def __repr__(self):
        return self.Title



