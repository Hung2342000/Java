$(document).ready(function () {
    $("#addformmonhoc").validate({
        rules: {
            mamonhoc: {
                required : true,
            },
            tenmonhoc: {
                required : true,
            },


        },
        messages: {
            mamonhoc:{
                required: "Mã không được bỏ trống"
            },
            tenmonhoc:{
                required: "Bạn phải nhập tên"
            },

        }
    });
    $("#edit_monhoc").validate({
        rules: {
            tenmonhocedit: {
                required : true,
            }

        },
        messages: {
            tenmonhocedit:{
                required: "Bạn phải nhập tên"
            }
        }
    });
    $("#btn_add_monhoc").click(function () {
        $('#modaladdmonhoc').modal('show');

        $("#sb").click(function (e) {
            let tenmonhoc = $("#tenmonhoc").val();
            let mamochoc = $("#mamonhoc").val();
            let data = {
                "maMonHoc": mamochoc,
                "tenMonHoc": tenmonhoc ,
            };
            console.log(data);
            if($("#addformmonhoc").valid()) {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/admin/monhoc/post",
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function (response) {
                        console.log(response);
                        location.reload();
                        $('#modaladdmonhoc').modal("hide");
                    },
                    error:function (){
                        alert("Môn học đã tồn tại");
                    }

                })
            }

        });

    });
    var id;
    $(".editmonhoc").click(function () {
        $("#modaleditmonhoc").modal("show");

        id = $(this).data("id");
        console.log(id);
        $.ajax({
            type:"GET",
            url : "/admin/monhoc/get/" + id,
            success: function (response){
                $("#tenmonhocedit").val(response.tenMonHoc);
            }

        });

        $("#edit").click(function (){
            let tenmonhoc = $("#tenmonhocedit").val();
            let data = {
                "tenMonHoc": tenmonhoc ,
            };
            console.log(data);
            if($("#edit_monhoc").valid()) {
                $.ajax({
                    type: "PUT",
                    contentType: "application/json",
                    url: "/admin/monhoc/put/" + id,
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function () {
                        location.reload();
                        console.log("hưng");
                        $("#modaleditmonhoc").modal("hide");
                    }
                })
            }
        })
    })

    $(".deletemonhoc").click(function () {
        $("#modaldeletemonhoc").modal("show");

        id = $(this).data("id");
        console.log(id);

        $("#delete").click(function (){

            $.ajax({

                url: "/admin/monhoc/delete/" + id,
                type: "DELETE",

                success: function (response){
                    console.log(response);
                    $("#"+id).remove();
                    $("#modaldeletemonhoc").modal("hide");
                },
                error: function (){
                  alert("Không hợp lệ");
                }
            })

        })
    });
});