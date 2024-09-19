$(document).ready(function () {
    // Toggle login popup
    $('#login-link').click(function (e) {
        e.preventDefault();
        $('#login-popup').fadeIn();
    });

    $('#logout-link').click(function (e) {
        e.preventDefault();
        $('#login-popup').fadeOut();
    });

    $('.close').click(function () {
        $('#login-popup').fadeOut();
    });

    // Recipe image navigation
        $('.img-prev').click(function () {
            let $card = $(this).closest('.card');
            let $images = $card.find('img.card-img-top');
            let currentIndex = $images.index($card.find('img.card-img-top:visible'));

            // Hide the current image and show the previous one
            $images.eq(currentIndex).hide();
            let prevIndex = (currentIndex - 1 + $images.length) % $images.length;
            $images.eq(prevIndex).show();
        });

        $('.img-next').click(function () {
            let $card = $(this).closest('.card');
            let $images = $card.find('img.card-img-top');
            let currentIndex = $images.index($card.find('img.card-img-top:visible'));

            // Hide the current image and show the next one
            $images.eq(currentIndex).hide();
            let nextIndex = (currentIndex + 1) % $images.length;
            $images.eq(nextIndex).show();
        });
});