from django.db import models
from django.contrib.auth.models import User

User._meta.get_field('email')._unique = True


class astronomers(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=60)
    abrName = models.CharField(max_length=30, verbose_name="abbreviated name")
    dateOfBirth = models.DateField(verbose_name="date of birth")
    dateOfDeath = models.DateField(verbose_name="date of death", blank=True)
    fave = models.ManyToManyField(User, related_name='astronomer_favorite')

    class Meta:
        db_table = "Astronomers"

    def __str__(self):
        return self.name


class minorPlanets(models.Model):
    name = models.IntegerField()
    discoverer = models.ForeignKey(astronomers, on_delete=models.DO_NOTHING)
    diameter = models.IntegerField(verbose_name="diameter(in km.)")
    fave = models.ManyToManyField(User, related_name='planet_favorite')

    class Meta:
        db_table = "Minor Planets"

    def __int__(self):
        return self.name


class userInfo(models.Model):
    name = models.CharField(max_length=60)
    age = models.IntegerField()
    educationInfo = models.CharField(max_length=120)
    user = models.ForeignKey(User, blank=False, null=False, on_delete=models.CASCADE)

    class Meta:
        db_table = "UserInfo"

    def __str__(self):
        return self.name
