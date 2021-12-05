import { apiInstance } from "./index.js";

const api = apiInstance();

function getNoticeList(param, success, fail) {
  api.get(`/notice`, { params: param }).then(success).catch(fail);
}

function registerNotice(notice, success, fail) {
  api.post(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

function getNotice(noticeNo, success, fail) {
  api.get(`/notice/${noticeNo}`).then(success).catch(fail);
}

function getNoticeInfo(noticeNo, success, fail) {
  api.get(`/notice/info/${noticeNo}`).then(success).catch(fail);
}

function modifyNotice(notice, success, fail) {
  api.put(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

function deleteNotice(noticeNo, success, fail) {
  api.delete(`/notice/${noticeNo}`).then(success).catch(fail);
}

export {
  getNoticeList,
  registerNotice,
  getNotice,
  getNoticeInfo,
  modifyNotice,
  deleteNotice,
};
