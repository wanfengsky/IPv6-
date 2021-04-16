(window.loadData || (window.loadData = [])).push(function() {
    var htm = [
        '<div class="col-sm-%s">',
            '<div class="ibox">',
                '<div class="ibox-content">',
                    '<h4 style="overflow: hidden;text-overflow:ellipsis;white-space:nowrap;width:100%;line-height:120%">%s</h4>',
                    '<div style="height:120px;background: url(\'%s\') no-repeat center center;background-size: 100% auto;margin:10px 0"></div>',
                    '<p style="overflow: hidden;text-overflow:ellipsis;width:100%;display:-webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp:3;">%s</p>',
                    '<div class="row m-t-sm">',
                        '<div class="col-sm-12 text-right">',
                            '<a class="btn btn-primary btn-xs" onclick="$.modal.openTab(\'课程查看\', \'/course/chapter/chapterList?courseId=%s\')">',
                                '<i class="fa fa-hand-pointer-o"></i> 查看',
                            '</a>',
                        '</div>',
                    '</div>',
                '</div>',
            '</div>',
        '</div>'
    ].join('');

    $.post("/course/course/list", function(data) {
        if(!data || data.code != 0 || data.rows.length <= 0) {
            $("#course-table").html('<div class="col-sm-12">当前暂无您的课程！</div>');
        } else {
            var htmls = '';
            var minLen = Math.min(data.rows.length,3);
            for (var i=0;i < minLen;i++){
                var course = data.rows[i];
                htmls += $.common.sprintf(htm, minLen<3?6:4, course.courseName, course.courseImage, course.courseInfo, course.courseId);
            }
            $("#course-table").html(htmls);
        }
    });
});