window.com_github_appreciated_circular_progressbar_CircularProgressBar = function () {

        var circularProgressbar = this.getElement();
        circularProgressbar.innerHTML = '<svg class=""id="animated" viewbox="0 0 100 100">' +
        '<circle cx="50" cy="50" r="45" fill="#044B94" fill-opacity="0.0"/>' +
        '<path id="progress-background" stroke-linecap="round" stroke-width="3" stroke="#aaa" fill="none" stroke-dasharray="251.2,251.2" d="M50 10 a 40 40 0 0 1 0 80 a 40 40 0 0 1 0 -80"></path>'+
        '<path id="progress" stroke-linecap="round" stroke-width="3" stroke="#197de1" fill="none" stroke-dasharray="0,251.2"' +
        'd="M50 10 a 40 40 0 0 1 0 80 a 40 40 0 0 1 0 -80"></path>' +
        '<text id="count" x="50" y="50" text-anchor="middle" dy="7" font-size="17">100%</text>' +
        '</svg>';
        this.onStateChange = function () {
            animateProgress(this.getState().progress);
        }

    function animateProgress(percent = 0) {
        if (percent !== 'undefined') {
            console.log(percent);
            var progress = circularProgressbar.querySelector('#progress');
            var text = circularProgressbar.querySelector('#count');
            var animationStep = progress.getAttribute('stroke-dasharray').split(",")[0];
            var id = setInterval(frame, 10);
            var grow = animationStep < 251.2 * (percent / 100);
            function frame() {
                if (grow) {
                    if (animationStep >= 251.2 * (percent / 100)) {
                        progress.setAttribute('stroke-dasharray', 251.2 * (percent / 100) + ',251.2');
                        text.textContent= parseInt(percent)+ '%';
                        clearInterval(id);
                    } else {
                        progress.setAttribute('stroke-dasharray', animationStep + ',251.2');
                        text.textContent= parseInt(animationStep / 251.2 * 100)+ '%';
                        animationStep++;
                    }
                } else {
                    if (animationStep < 251.2 * (percent / 100)) {
                        progress.setAttribute('stroke-dasharray', 251.2 * (percent / 100) + ',251.2');
                        text.textContent= parseInt(percent)+ '%';
                        clearInterval(id);
                    } else {
                        progress.setAttribute('stroke-dasharray', animationStep + ',251.2');
                        text.textContent= parseInt(animationStep / 251.2 * 100)+ '%';
                        animationStep--;
                    }
                }
            }
        }
    }

};