/**
 * 用户表管理管理初始化
 */
var User = {
    id: "UserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
User.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
    ];
};

/**
 * 检查是否选中
 */
User.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        User.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户表管理
 */
User.openAddUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户表管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/user/user_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户表管理详情
 */
User.openUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户表管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/user/user_update/' + User.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户表管理
 */
User.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/user/delete", function (data) {
            Feng.success("删除成功!");
            User.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("userId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户表管理列表
 */
User.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    User.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = User.initColumn();
    var table = new BSTable(User.id, "/user/list", defaultColunms);
    table.setPaginationType("client");
    User.table = table.init();
});
