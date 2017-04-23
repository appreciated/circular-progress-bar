window.com_github_appreciated_circularprogressbar_CircularProgressBar = function () {

    var circularProgressbar = this.getElement();
    circularProgressbar.innerHTML = '<svg class="" id="animated" viewBox="0 0 100 100">'+
    '<path id="progress-background" stroke-linecap="round" stroke-width="5" stroke="#d4d4d4" fill="none" stroke-dasharray="251.2,251.2" d="M50 10 a 40 40 0 0 1 0 80 a 40 40 0 0 1 0 -80"></path>'+ // Track
    '<path id="progress-border" stroke-linecap="round" stroke-width="5" stroke="#1362b1" fill="none" stroke-dasharray="0,251.2" d="M50 10 a 40 40 0 0 1 0 80 a 40 40 0 0 1 0 -80"></path>'+ // Border
    '<path id="progress-inner" stroke-linecap="round" stroke-width="4" stroke="#197de1" fill="none" stroke-dasharray="0,251.2" d="M50 10 a 40 40 0 0 1 0 80 a 40 40 0 0 1 0 -80"></path>'+ // Inner
    '<text id="count" x="50" y="50" text-anchor="middle" dy="7" font-size="17">0%</text>' + // Label
    '</svg>';
    this.onStateChange = function () {
        animateProgress(this.getState().value);
    }

    function animateProgress(percent = 0) {
        if (percent !== 'undefined') {
            console.log(percent);
            var progressBorder = circularProgressbar.querySelector('#progress-border');
            var progressInner = circularProgressbar.querySelector('#progress-inner');
            var text = circularProgressbar.querySelector('#count');
            var animationStep = progressBorder.getAttribute('stroke-dasharray').split(",")[0];
            var id = setInterval(frame, 10);
            var grow = animationStep < 251.2 * (percent);
            function frame() {
                if (grow) {
                    if (animationStep >= 251.2 * (percent)) {
                        progressBorder.setAttribute('stroke-dasharray', 251.2 * (percent) + ',251.2');
                        progressInner.setAttribute('stroke-dasharray', 251.2 * (percent) + ',251.2');
                        text.textContent = parseInt(percent*100)+ '%';
                        clearInterval(id);
                    } else {
                        progressBorder.setAttribute('stroke-dasharray', animationStep + ',251.2');
                        progressInner.setAttribute('stroke-dasharray', animationStep + ',251.2');
                        text.textContent = parseInt(animationStep / 251.2 * 100)+ '%';
                        animationStep++;
                    }
                } else {
                    if (animationStep < 251.2 * (percent)) {
                        progressBorder.setAttribute('stroke-dasharray', 251.2 * (percent) + ',251.2');
                        progressInner.setAttribute('stroke-dasharray', 251.2 * (percent) + ',251.2');
                        text.textContent = parseInt(percent*100)+ '%';
                        clearInterval(id);
                    } else {
                        progressBorder.setAttribute('stroke-dasharray', animationStep + ',251.2');
                        progressInner.setAttribute('stroke-dasharray', animationStep + ',251.2');
                        text.textContent = parseInt(animationStep / 251.2*100)+ '%';
                        animationStep--;
                    }
                }
            }
        }
    }

};