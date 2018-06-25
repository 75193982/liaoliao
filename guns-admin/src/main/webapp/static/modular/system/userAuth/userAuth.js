/**
 * 授权管理初始化
 */
var UserAuth = {
    id: "UserAuthTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserAuth.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'headImageUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'token', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'gender', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'type', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserAuth.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserAuth.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加授权
 */
UserAuth.openAddUserAuth = function () {
    var index = layer.open({
        type: 2,
        title: '添加授权',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userAuth/userAuth_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看授权详情
 */
UserAuth.openUserAuthDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '授权详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userAuth/userAuth_update/' + UserAuth.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除授权
 */
UserAuth.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/userAuth/delete", function (data) {
            Feng.success("删除成功!");
            UserAuth.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userAuthId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询授权列表
 */
UserAuth.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    UserAuth.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = UserAuth.initColumn();
    var table = new BSTable(UserAuth.id, "/userAuth/list", defaultColunms);
    table.setPaginationType("client");
    UserAuth.table = table.init();
});
