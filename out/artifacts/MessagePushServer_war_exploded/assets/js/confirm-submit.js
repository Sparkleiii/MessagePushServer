//接单验证
function take_order(data) {
    document.getElementById('row2').style.display='block';
    document.getElementById('row1').style.display='none';
    document.getElementById('eno').value = data;
}
function confirm_order(data) {
    document.getElementById('row2').style.display='block';
    document.getElementById('row1').style.display='none';
    document.getElementById('orderno').value = data;
}
function deleteExpress(data) {
    document.getElementById('row2').style.display='block';
    document.getElementById('row1').style.display='none';
    document.getElementById('eno').value = data;
    document.getElementById('eno2').value = data;
}
function deleteOrders(data) {
    document.getElementById('row2').style.display='block';
    document.getElementById('row1').style.display='none';
    document.getElementById('orderno').value = data;
    document.getElementById('orderno2').value = data;
}


function  cancel() {
    document.getElementById('row2').style.display='none';
    document.getElementById('row1').style.display='block';
}


