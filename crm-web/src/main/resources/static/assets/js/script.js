window.onload=function() {

  const hamBurger = document.getElementById("toggleButton");
  hamBurger.addEventListener("click", function () {
    document.querySelector("#sidebar").classList.toggle("expand");
  });
}
