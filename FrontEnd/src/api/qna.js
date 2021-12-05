import { apiInstance } from "./index.js";

const api = apiInstance();

function getQnaList(param, success, fail) {
  api.get(`/qna`, { params: param }).then(success).catch(fail);
}

function registerQna(qna, success, fail) {
  api.post(`/qna`, JSON.stringify(qna)).then(success).catch(fail);
}
function registerQnaReply(qna, success, fail) {
  api.post(`/qna/reply`, JSON.stringify(qna)).then(success).catch(fail);
}

function getQna(qnaNo, success, fail) {
  api.get(`/qna/${qnaNo}`).then(success).catch(fail);
}

function getQnaInfo(qnaNo, success, fail) {
  api.get(`/qna/info/${qnaNo}`).then(success).catch(fail);
}

function modifyQna(qna, success, fail) {
  api.put(`/qna`, JSON.stringify(qna)).then(success).catch(fail);
}

function deleteQna(qnaNo, success, fail) {
  api.delete(`/qna/${qnaNo}`).then(success).catch(fail);
}

export {
  getQnaList,
  registerQna,
  registerQnaReply,
  getQna,
  getQnaInfo,
  modifyQna,
  deleteQna,
};
