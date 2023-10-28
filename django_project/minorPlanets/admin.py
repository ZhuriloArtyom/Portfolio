from django.contrib import admin
import minorPlanets.models as models
# Register your models here.

admin.site.register(models.astronomers)
admin.site.register(models.minorPlanets)
admin.site.register(models.userInfo)