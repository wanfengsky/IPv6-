(window.loadData || (window.loadData = [])).push(function() {
    initSigninTable();
});

function initSigninTable() {
    //var removeFlag = [[${@permission.hasPermi('course:signrecord:remove')}]];
    var prefix = ctx + "course/signin";
    var options = {
        url: prefix + "/getNowSignIn",
        viewUrl: prefix + "/view",
        id: "signin-table",
        toolbar: "signin-toolbar",
        modalName: "签到列表",
        columns: [
            {
                field: 'signinId',
                title: '签到表编号',
                visible: false
            },

            {
                field: 'signinName',
                title: '签到表'
            },
            {
                field: 'endTime',
                title: '结束时间'
            },

            {
                title: '操作',
                align: 'center',
                formatter: function(value, row, index) {
                    var actions = [];
                    actions.push('<a class="btn btn-success btn-xs " href="javascript:void(0)" onclick="signIn(\'' + row.signinId + '\')"><i class="fa fa-edit"></i>签到</a> ');
                    return actions.join('');
                }
            }]
    };
    $.table.init(options);
}

function viewSignRecord(signinId) {
    var url = ctx + 'course/signrecord/view/signrecordView?signinId=' + signinId;
    $.modal.openTab('签到记录', url);
}

function signIn(signinId) {
    var prefix = ctx + "course/signrecord";
    $.ajax({
        url:prefix + "/add?signinId="+signinId,
        type:"POST",
        success:function (data){
            $("#signin-table").bootstrapTable('refresh');

        }
    });
}



