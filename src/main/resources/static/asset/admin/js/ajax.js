$.ajaxSetup({
    headers: {
        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
    }
});
$(document).ready(function () {

    //delete category
    $('.delete').click(function () {
        var id1 = $(this).data('id');
        $('.dele').click(function () {
            $.ajax({
                url: 'admin/category/' + id1,
                type: 'delete',
                success: function (res) {

                }
            });
        });
    });
});
