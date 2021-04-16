(window.loadData || (window.loadData = [])).push(function() {

    initTable();
});

function initTable() {

    var prefix = ctx + "system/notice";
    var options = {
        url: prefix + "/list?status=0",
        viewUrl: prefix + "/view",
        id: "notice-table",
        toolbar: "notice-toolbar",
        modalName: "公告",
        columns: [
            {
                field : 'noticeId',
                title : '序号',
                visible: false
            },
            {
                field : 'noticeTitle',
                title : '公告标题'
            },

            {
                field: 'createTime',
                title: '创建时间',
                sortable: true
            },
            {
                title: '操作',
                align: 'center',
                formatter: function(value, row, index) {
                    var actions = [];
                    actions.push('<a class="btn btn-success btn-xs " href="javascript:void(0)" onclick="viewNotice(\'' + row.noticeId + '\')"><i class="fa fa-edit"></i>查看</a> ');
                    return actions.join('');
                }
            }]
    };
    $.table.init(options);
}
function viewNotice(noticeId) {
    var url = ctx + 'system/notice/view?noticeId=' + noticeId;
    $.modal.openTab('通知详情', url);
}




