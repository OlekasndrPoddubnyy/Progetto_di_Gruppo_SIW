const menuToggle = document.querySelector('.menuToggle');
const navigation = document.querySelector('.navigation');
const main = document.querySelector('.main');
menuToggle.onclick = function (){
    navigation.classList.toggle('open');
    main.classList.toggle('active');
}

const list = document.querySelectorAll('.list');
function activeLink(){
    list.forEach((item) =>
    item.classList.remove('active'));
    this.classList.add('active');
}

list.forEach((item) =>
item.addEventListener('click', activeLink));