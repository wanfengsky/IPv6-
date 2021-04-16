(window.loadData || (window.loadData = [])).push(function() {
    var prefix = "/course/hanswer";
    var options = {
        id: "hanswer-table",
        toolbar: "hanswer-toolbar",
        url: prefix + "/list?score=-1",
        updateUrl: prefix + "/edit/{id}",
        modalName: "待批改作业列表",
        columns: [{
            field: 'answerId',
            title: 'ID'
        }, {
            field: 'groupName',
            title: '班级'
        }, {
            field: 'userName',
            title: '学生姓名'
        }, {
            field: 'submitTime',
            title: '提交时间'
        }, {
            field: 'answerContent',
            title: '作业内容',
            formatter: function(value, row, index) {
               return '<p style="overflow: hidden;text-overflow:ellipsis;white-space: nowrap;width: 300px;">'+htmlText(value)+'</p>';
            }
        }, {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-primary btn-xs" href="javascript:void(0)" onclick="$.operate.edit(\'' + row.answerId + '\')"><i class="fa fa-check"></i>批阅</a> ');
                return actions.join('');
            }
        }]
    };
    $.table.init(options);
});