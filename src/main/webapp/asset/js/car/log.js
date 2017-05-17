function showInfo(userName, sex, phone, email) {
    $('#info-modal').modal('show');
    $('#iuserName').val(userName);
    $('#isex').val(sex);
    $('#iphone').val(phone);
    $("#iemall").val(email);
}