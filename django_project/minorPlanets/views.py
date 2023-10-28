from django.contrib.auth import authenticate, login, logout, update_session_auth_hash
from django.contrib.auth.forms import PasswordChangeForm
from django.shortcuts import render, redirect
from django.core.paginator import Paginator, EmptyPage, PageNotAnInteger
import minorPlanets.models as mod
from .forms import SignUpForm, PersonalInfo
from django.shortcuts import get_object_or_404
from django.http import HttpResponseRedirect


def mp_list(request, string="default"):
    if string == "faved":
        mp_list = request.user.planet_favorite.all()
    else:
        mp_list = mod.minorPlanets.objects.all()
    astr_list = mod.astronomers.objects.all()
    page = request.GET.get('page', 1)

    paginator = Paginator(mp_list, 3)
    try:
        mps = paginator.page(page)
    except PageNotAnInteger:
        mps = paginator.page(1)
    except EmptyPage:
        mps = paginator.page(paginator.num_pages)
    return render(request, "minorPlanetsbase.html", {"mps": mps, "astr_list": astr_list})


def astronom_list(request, string="default"):
    if string == "faved":
        astr_list = request.user.astronomer_favorite.all()
    else:
        astr_list = mod.astronomers.objects.all()

    mp_list = mod.minorPlanets.objects.all()
    page = request.GET.get('page', 1)

    paginator = Paginator(astr_list, 3)
    try:
        astrs = paginator.page(page)
    except PageNotAnInteger:
        astrs = paginator.page(1)
    except EmptyPage:
        astrs = paginator.page(paginator.num_pages)

    return render(request, "astronomersList.html", {"mp_list": mp_list, "astrs": astrs})


# Create your views here.
def signup(request):
    if request.method == 'POST':
        form = SignUpForm(request.POST)
        if form.is_valid():
            user = form.save()
            user.refresh_from_db()
            # load the profile instance created by the signal
            user.save()
            raw_password = form.cleaned_data.get('password1')

            # login user after signing up
            user = authenticate(username=user.username, password=raw_password)
            login(request, user)

            # redirect user to home page
            return redirect('home')
    else:
        form = SignUpForm()
    return render(request, 'sign_up.html', {'form': form})


def customlogin(request):
    if request.method == 'GET':
        return render(request, 'login.html')
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']
        user = authenticate(request, username=username, password=password)
        if user is not None:
            login(request, user)
            # Redirect to a success page.
            return redirect('home')
        else:
            return render(request, 'login.html')


def logout_request(request):
    logout(request)
    return redirect("home")


def change_password(request):
    if request.method == 'POST':
        form = PasswordChangeForm(request.user, request.POST)
        if form.is_valid():
            user = form.save()
            update_session_auth_hash(request, user)
            return redirect('home')
    else:
        form = PasswordChangeForm(request.user)
    return render(request, 'change_password.html', {
        'form': form
    })


def getUserInfo(request):
    if request.method == 'POST':
        form = PersonalInfo(request.POST)
        if form.is_valid():
            info = mod.userInfo()
            info.name = form.cleaned_data['name']
            info.age = form.cleaned_data['age']
            info.educationInfo = form.cleaned_data['education']
            info.user = request.user
            info.save()
            return redirect('home')
    else:
        form = PersonalInfo()
    return render(request, 'input_info.html', {'form': form})


def PlanetFave(request, pk):
    planet = get_object_or_404(mod.minorPlanets, id=request.POST.get('planet_id'))
    if planet.fave.filter(id=request.user.id).exists():
        planet.fave.remove(request.user)
    else:
        planet.fave.add(request.user)

    return HttpResponseRedirect(request.META.get('HTTP_REFERER'))


def AstronomerFave(request, pk):
    astronomer = get_object_or_404(mod.astronomers, id=request.POST.get('astronomer_id'))
    if astronomer.fave.filter(id=request.user.id).exists():
        astronomer.fave.remove(request.user)
    else:
        astronomer.fave.add(request.user)

    return HttpResponseRedirect(request.META.get('HTTP_REFERER'))


def home(request):
    return render(request, 'home.html')
