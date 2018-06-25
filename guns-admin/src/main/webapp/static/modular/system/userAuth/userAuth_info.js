/**
 * 初始化授权详情对话框
 */
var UserAuthInfoDlg = {
    userAuthInfoData : {}
};

/**
 * 清除数据
 */
UserAuthInfoDlg.clearData = function() {
    this.userAuthInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserAuthInfoDlg.set = function(key, val) {
    this.userAuthInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserAuthInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserAuthInfoDlg.close = function() {
    parent.layer.close(window.parent.UserAuth.layerIndex);
}

/**
 * 收集数据
 */
UserAuthInfoDlg.collectData = function() {
    this
    .set('id')
    .set('headImageUrl')
    .set('token')
    .set('userId')
    .set('name')
    .set('gender')
    .set('type');
}

/**
 * 提交添加
 */
UserAuthInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userAuth/add", function(data){
        Feng.success("添加成功!");
        window.parent.UserAuth.table.refresh();
        UserAuthInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userAuthInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserAuthInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userAuth/update", function(data){
        Feng.success("修改成功!");
        window.parent.UserAuth.table.refresh();
        UserAuthInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userAuthInfoData);
    ajax.start();
}

$(function() {

});
