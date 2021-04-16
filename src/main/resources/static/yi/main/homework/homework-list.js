(window.loadData || (window.loadData = [])).push(function() {
    var prefix = "/course/homework";
    var options = {
        id: "homework-table",
        toolbar: "homework-toolbar",
        url: prefix + "/listForMe?isOver=0",
        modalName: "待完成作业",
        columns: [
        {field: 'allocatId', title: '分配编号', visible: false},
        {field: 'groupId', title: '班级编号', visible: false},
        {field: 'answerId', title: '答案编号', visible: false},
        {field: 'chapterId', title: '章节编号', visible: false},
        {field: 'courseId', title: '课程编号', visible: false},
        {field: 'homeworkId', title: '作业编号'},
        {field: 'homeworkName', title: '作业名'},
        {field: 'courseName', title: '课程'},
        {field: 'chapterName', title: '章节'},
        {field: 'groupName', title: '班级'},
        {field: 'receiveCount', title: '完成/总数', formatter: function(value, row, index) {
            return (row.summitCount ? row.summitCount : 0) + '/' + value;
        }},
        {field: 'isOver', title: '是否完成',formatter: function(value, row, index) {
               return $.table.selectDictLabel(yesNo, value);
            }
        },
        {field: 'deadline', title: '截止时间'},
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
                var actions = [];
                actions.push('<a class="btn btn-primary btn-xs" href="javascript:void(0)" onclick="toCommit(' + index + ')"><i class="fa fa-check"></i>提交</a> ');
                return actions.join('');
            }
        }]
    };
    $.table.init(options);
});

// 展示作业提交框
function toCommit(index){
    var row = $("#homework-table").bootstrapTable('getData')[index];
    var url = '/course/homework/toAnswer?homeworkId=' + row.homeworkId + '&groupId=' + row.groupId;
    $.modal.open("提交作业", url);
}