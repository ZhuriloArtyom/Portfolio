from flask_wtf import FlaskForm
from wtforms import StringField, PasswordField, SubmitField, BooleanField
from wtforms.validators import DataRequired, Email, EqualTo, Length


# class RegistrationForm(FlaskForm):
#     username = StringField('username', validators=[DataRequired()])
#     email = StringField('Email', validators=[DataRequired(), Email()])
#     password1 = PasswordField('Password', validators=[DataRequired()])
#     password2 = PasswordField('Confirm Password', validators=[DataRequired(), EqualTo('password1')])
#     submit = SubmitField('Register')
#
# 
# class LoginForm(FlaskForm):
#     email = StringField('Email', validators=[DataRequired(), Email()])
#     password = PasswordField('Password', validators=[DataRequired()])
#     remember = BooleanField('Remember Me')
#     submit = SubmitField('Login')
#

class PostForm(FlaskForm):
    Title = StringField('Title', validators=[DataRequired(), Length(min=3, max=128)])
    Contents = StringField('Contents', validators=[Length(max=1000)])
    submit = SubmitField('Post')
