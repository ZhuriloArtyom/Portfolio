# Generated by Django 4.1.1 on 2022-09-26 23:36

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('minorPlanets', '0003_alter_astronomers_table_alter_minorplanets_table'),
    ]

    operations = [
        migrations.AlterModelTable(
            name='minorplanets',
            table='Minor_Planet',
        ),
    ]