from django.contrib.auth.models import User
from django.contrib.auth.forms import UserCreationForm
from django import forms
from crispy_forms.helper import FormHelper
from crispy_forms.layout import Layout, Fieldset, Submit, HTML


class SignUpForm(UserCreationForm):
    class Meta:
        model = User
        fields = ('username', 'email', 'password1', 'password2',)


class PersonalInfo(forms.Form):
    name = forms.CharField(label="Your name", max_length=60)
    age = forms.IntegerField(label="Your age")
    education = forms.CharField(label="Information about your education", max_length=120)

    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.helper = FormHelper()
        self.helper.layout = Layout(
            HTML("""<h2 style = "background-color: rgba(200, 200, 225, 0.85)">"""),
            Fieldset(
                'Tell us about yourself',
                'name',
                'age',
                'education',
            ),
            HTML("""</h2>""")
)