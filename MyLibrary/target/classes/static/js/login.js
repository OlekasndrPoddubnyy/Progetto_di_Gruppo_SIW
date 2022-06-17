const containerlog = document.querySelector(".login"),
    containersing = document.querySelector(".signup"),
    pwShowHide = document.querySelectorAll(".showHidePw"),
    pwFields = document.querySelectorAll(".password"),
    signUp = document.querySelector(".signup-link"),
    blur = document.querySelector(".sfondo"),
    pulsante = document.querySelector("#login-page"),
    chiudiform = document.querySelectorAll(".close-bar"),
    login = document.querySelector(".login-link");

//   js code to show/hide password and change icon
pwShowHide.forEach(eyeIcon =>{
    eyeIcon.addEventListener("click", ()=>{
        pwFields.forEach(pwField =>{
            if(pwField.type ==="password"){
                pwField.type = "text";

                pwShowHide.forEach(icon =>{
                    icon.classList.replace("uil-eye-slash", "uil-eye");
                })
            }else{
                pwField.type = "password";

                pwShowHide.forEach(icon =>{
                    icon.classList.replace("uil-eye", "uil-eye-slash");
                })
            }
        })
    })
})

pulsante.addEventListener("click", ( )=>{
    containerlog.classList.add("active");
    pulsante.classList.add("blur");
})

chiudiform.forEach(closeIcon =>{
    closeIcon.addEventListener("click", ( )=>{
        containerlog.classList.remove("active");
        containersing.classList.remove("active");
        pulsante.classList.remove("blur");
    })
})

// js code to appear signup and login form
signUp.addEventListener("click", ( )=>{
    containersing.classList.add("active");
    containerlog.classList.remove("active");
})

login.addEventListener("click", ( )=>{
    containerlog.classList.add("active");
    containersing.classList.remove("active");
})