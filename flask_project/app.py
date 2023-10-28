from flask import Flask
from config import Config
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from flask_mail import Mail
from flask_login import LoginManager

app = Flask(__name__)
app.config.update(
    DEBUG=True,
    MAIL_SERVER='smtp.gmail.com',
    MAIL_PORT=587,
    MAIL_USE_TLS=True,
    MAIL_USE_SSL=False,
    MAIL_USERNAME='ta7909336@gmail.com',
    MAIL_DEFAULT_SENDER = 'ta7909336@gmail.com',
    MAIL_PASSWORD='bfurslvhjgzhsmbo',
    GOOGLE_CLIENT_ID = '663404679617-2dlgsijdcjp4nrha4vu7co87l0s7qklo.apps.googleusercontent.com',
    GOOGLE_CLIENT_SECRET ='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'
)
#Testpassword1
mail = Mail(app)
app.config.from_object(Config)
db = SQLAlchemy(app)
migrate = Migrate(app, db)
app.config['SECRET_KEY'] = 'any secret string'
login_manager = LoginManager()
login_manager.init_app(app)
login_manager.login_view = 'login'
import routes


if __name__ == '__main__':
    app.run()
