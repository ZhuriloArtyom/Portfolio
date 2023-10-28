# Generated by Django 4.1.1 on 2022-11-08 02:31

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('minorPlanets', '0007_remove_favplanets_user_minorplanets_fave_and_more'),
    ]

    operations = [
        migrations.CreateModel(
            name='userInfo',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=60)),
                ('age', models.IntegerField()),
                ('educationInfo', models.CharField(max_length=120)),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
            options={
                'db_table': 'UserInfo',
            },
        ),
        migrations.RemoveField(
            model_name='favplanets',
            name='user',
        ),
        migrations.DeleteModel(
            name='favMPlanets',
        ),
        migrations.DeleteModel(
            name='favPlanets',
        ),
    ]