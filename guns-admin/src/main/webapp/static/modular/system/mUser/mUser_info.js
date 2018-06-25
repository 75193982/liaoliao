/**
 * 初始化用户管理表详情对话框
 */
var MUserInfoDlg = {
    mUserInfoData : {}
};

/**
 * 清除数据
 */
MUserInfoDlg.clearData = function() {
    this.mUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MUserInfoDlg.set = function(key, val) {
    this.mUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MUserInfoDlg.close = function() {
    parent.layer.close(window.parent.MUser.layerIndex);
}

/**
 * 收集数据
 */
MUserInfoDlg.collectData = function() {
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
MUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.MUser.table.refresh();
        MUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.MUser.table.refresh();
        MUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mUserInfoData);
    ajax.start();
}

$(function() {

});
