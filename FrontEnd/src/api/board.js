import { apiInstance } from "./index.js";

const api = apiInstance();

function getBoardList(param, success, fail) {
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

function registerBoard(board, success, fail) {
  api.post(`/board`, JSON.stringify(board)).then(success).catch(fail);
}

function registerComment(comment, success, fail) {
  api.post(`/board/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

function getBoard(boardNo, success, fail) {
  api.get(`/board/${boardNo}`).then(success).catch(fail);
}

function getBoardInfo(boardNo, success, fail) {
  api.get(`/board/info/${boardNo}`).then(success).catch(fail);
}

function modifyBoard(board, success, fail) {
  api.put(`/board`, JSON.stringify(board)).then(success).catch(fail);
}

function deleteBoard(boardNo, success, fail) {
  api.delete(`/board/${boardNo}`).then(success).catch(fail);
}

function getComment(boardNo, success, fail) {
  api.get(`/board/comment/${boardNo}`).then(success).catch(fail);
}

function modifyComment(comment, success, fail) {
  api.put(`/board/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

function deleteComment(commentNo, success, fail) {
  api.delete(`/board/comment/${commentNo}`).then(success).catch(fail);
}

export {
  getBoardList,
  registerBoard,
  registerComment,
  getBoard,
  getBoardInfo,
  modifyBoard,
  deleteBoard,
  getComment,
  modifyComment,
  deleteComment,
};
