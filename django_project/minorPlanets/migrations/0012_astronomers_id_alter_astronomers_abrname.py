# Generated by Django 4.1.1 on 2022-12-13 06:55

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('minorPlanets', '0011_astronomers_fave'),
    ]

    operations = [
        migrations.AddField(
            model_name='astronomers',
            name='id',
            field=models.IntegerField(auto_created=True, default=1, primary_key=True, serialize=False),
            preserve_default=False,
        ),
        migrations.AlterField(
            model_name='astronomers',
            name='abrName',
            field=models.CharField(max_length=30, verbose_name='abbreviated name'),
        ),
    ]
