(function($){
  $(function(){

    $('.sidenav').sidenav();
    $('.parallax').parallax();

  }); // end of document ready
})(jQuery); // end of jQuery name space


document.addEventListener('DOMContentLoaded', function() {
  const datepickerInput = document.getElementById('icon_calendar');
  const datepickerInstance = M.Datepicker.init(datepickerInput, {
      autoClose: true,
      // You can add more options for the datepicker here
  });
});