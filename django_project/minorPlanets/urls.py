"""djangoProject URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
import debug_toolbar
import minorPlanets.views as views
from django.urls import include, path
from django.contrib.auth import views as auth_views

urlpatterns = [

    path('admin/', admin.site.urls),
    path('signup/', views.signup, name='signup'),
    path("logout/", views.logout_request, name="logout"),
    path('planet_fave/<int:pk>', views.PlanetFave, name="planet_fave"),
    path('astronomers_fave/<int:pk>', views.AstronomerFave, name="astronomers_fave"),
    path('__debug__/', include(debug_toolbar.urls)),
    path("login/", views.customlogin, name='login'),
    path('input_info/', views.getUserInfo, name='info'),
    path('mp/<str:string>/', views.mp_list, name='mp_list'),
    path('astronomers/<str:string>/', views.astronom_list, name='astronomer_list'),
    path("", views.home, name='home'),
    path("password/", views.change_password, name='change_password'),
]
