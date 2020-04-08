function viewportToPixels(value) {
    let parts = (String)(value).match(/([0-9\.]+)(vh|vw)/);
    let q = Number(parts[1]);
    let side = window[['innerHeight', 'innerWidth'][['vh', 'vw'].indexOf(parts[2])]];
    return side * (q/100);
}

function setMinHeightOn100Vh(id) {
    let height = (viewportToPixels('100vh') - 85) + 'px';
    document.getElementById(id).style.minHeight = String(height);
}
