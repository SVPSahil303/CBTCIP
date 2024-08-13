let time = 30 * 60;
const timerElement = document.getElementById('time');

function updateTimer() {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    timerElement.textContent = `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
    time--;
    if (time < 0) {
        clearInterval(timerInterval);
        document.querySelector('form').submit();
    }
}

const timerInterval = setInterval(updateTimer, 1000);
updateTimer();
