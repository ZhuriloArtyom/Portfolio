# Generated by Django 4.1.1 on 2022-12-13 06:26

from django.conf import settings
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('minorPlanets', '0008_userinfo_remove_favplanets_user_delete_favmplanets_and_more'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='astronomers',
            name='placeOfWork',
        ),
        migrations.AddField(
            model_name='astronomers',
            name='fave',
            field=models.ManyToManyField(related_name='astronomer_favorite', to=settings.AUTH_USER_MODEL),
        ),
    ]
