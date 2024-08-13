window.onload = function() {
    var timer = document.getElementById('time');
    var time = timer.textContent.split(':');
    var minutes = parseInt(time[0]);
    var seconds = parseInt(time[1]);
    var countdown = setInterval(function() {
        if (seconds === 0) {
            if (minutes === 0) {
                clearInterval(countdown);
                alert('Time is up! Submitting the exam.');
                document.querySelector('form').submit();
            } else {
                minutes--;
                seconds = 59;
            }
        } else {
            seconds--;
        }
        timer.textContent = minutes + ':' + (seconds < 10 ? '0' + seconds : seconds);
    }, 1000);
};
