# Generated by Django 4.1.1 on 2022-09-26 23:24

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('minorPlanets', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='minorplanets',
            name='discoverer',
            field=models.ForeignKey(on_delete=django.db.models.deletion.DO_NOTHING, to='minorPlanets.astronomers'),
        ),
    ]
