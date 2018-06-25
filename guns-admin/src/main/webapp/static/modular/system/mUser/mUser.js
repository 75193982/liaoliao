/**
 * 用户管理表管理初始化
 */
var MUser = {
    id: "MUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'loginName', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'userPass', visible: true, align: 'center', valign: 'middle'},
            {title: '用户姓名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'token', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'sex', visible: true, align: 'center', valign: 'middle'},
            {title: '登录时间', field: 'loginTime', visible: true, align: 'center', valign: 'middle'},
            {title: '上次登录时间', field: 'lastLoginTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户管理表
 */
MUser.openAddMUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户管理表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mUser/mUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户管理表详情
 */
MUser.openMUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户管理表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mUser/mUser_update/' + MUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户管理表
 */
MUser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/mUser/delete", function (data) {
            Feng.success("删除成功!");
            MUser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("mUserId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户管理表列表
 */
MUser.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MUser.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MUser.initColumn();
    var table = new BSTable(MUser.id, "/mUser/list", defaultColunms);
    table.setPaginationType("client");
    MUser.table = table.init();
});
