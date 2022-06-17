const menu = document.querySelector('#mobile-menu');
const menuLinks = document.querySelector('.navbar__menu');
const navLogo = document.querySelector('#navbar__logo');

// Display Mobile Menu
const mobileMenu = () => {
    menu.classList.toggle('is-active');
    menuLinks.classList.toggle('active');
};

menu.addEventListener('click', mobileMenu);

//Show active menu when scrolling
const highlightMenu = () => {
    const elem = document.querySelector('.highlight');
    const homeMenu = document.querySelector('#home-page');
    const filmMenu = document.querySelector('#films-page');
    const tvSerieMenu = document.querySelector('#tv-series-page');
    const bookMenu = document.querySelector('#books-page');
    const gameMenu = document.querySelector('#games-page');
    let scrollPos = window.scrollY;
    //console.log(scrollPos);

    // adds 'highlight' class to any menu items
    if(window.innerWidth > 960 && scrollPos < 600) {
        homeMenu.classList.add('highlight');
        filmMenu.classList.remove('highlight');
        return;
    } else if(window.innerWidth > 960 && scrollPos < 1600) {
        filmMenu.classList.add('highlight');
        homeMenu.classList.remove('highlight');
        tvSerieMenu.classList.remove('highlight');
        return;
    } else if(window.innerWidth > 960 && scrollPos < 2450) {
        tvSerieMenu.classList.add('highlight');
        filmMenu.classList.remove('highlight');
        bookMenu.classList.remove('highlight');
        return;
    } else if(window.innerWidth > 960 && scrollPos < 3300) {
        bookMenu.classList.add('highlight');
        tvSerieMenu.classList.remove('highlight');
        gameMenu.classList.remove('highlight');
        return;
    }else if(window.innerWidth > 960 && scrollPos < 4300) {
        gameMenu.classList.add('highlight');
        bookMenu.classList.remove('highlight');
        return;
    }

    if((elem && window.innerWidth < 960 && scrollPos< 600) || elem){
        elem.classList.remove('highlight');
    }
};

window.addEventListener('scroll', highlightMenu);
window.addEventListener('click', highlightMenu);

//Close mobile Menu when clicking on a menu item
const hideMobileMenu = () => {
    const menuBars = document.querySelector('.is-active');
    if(window.innerWidth <= 768 && menuBars) {
        menu.classList.toggle('is-active');
        menuLinks.classList.remove('active');
    }
};

menuLinks.addEventListener('click', hideMobileMenu);
navLogo.addEventListener('click', hideMobileMenu);