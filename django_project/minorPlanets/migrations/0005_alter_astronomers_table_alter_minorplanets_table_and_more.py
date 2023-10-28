# Generated by Django 4.1.1 on 2022-11-06 17:39

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('minorPlanets', '0004_alter_minorplanets_table'),
    ]

    operations = [
        migrations.AlterModelTable(
            name='astronomers',
            table='Astronomers',
        ),
        migrations.AlterModelTable(
            name='minorplanets',
            table='Minor Planets',
        ),
        migrations.CreateModel(
            name='favPlanets',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.DO_NOTHING, to='minorPlanets.minorplanets')),
            ],
            options={
                'db_table': 'UserPlanet',
            },
        ),
    ]
