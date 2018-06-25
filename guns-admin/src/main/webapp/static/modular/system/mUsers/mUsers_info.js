/**
 * 初始化用户管理表详情对话框
 */
var MUsersInfoDlg = {
    mUsersInfoData : {}
};

/**
 * 清除数据
 */
MUsersInfoDlg.clearData = function() {
    this.mUsersInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MUsersInfoDlg.set = function(key, val) {
    this.mUsersInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MUsersInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MUsersInfoDlg.close = function() {
    parent.layer.close(window.parent.MUsers.layerIndex);
}

/**
 * 收集数据
 */
MUsersInfoDlg.collectData = function() {
    this
    .set('id')
    .set('loginName')
    .set('userPass')
    .set('userName')
    .set('mobile')
    .set('token')
    .set('createTime')
    .set('sex')
    .set('loginTime')
    .set('lastLoginTime');
}

/**
 * 提交添加
 */
MUsersInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mUsers/add", function(data){
        Feng.success("添加成功!");
        window.parent.MUsers.table.refresh();
        MUsersInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mUsersInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MUsersInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mUsers/update", function(data){
        Feng.success("修改成功!");
        window.parent.MUsers.table.refresh();
        MUsersInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mUsersInfoData);
    ajax.start();
}

$(function() {

});
