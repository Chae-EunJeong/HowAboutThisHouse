import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(email, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${email}`).then(success).catch(fail);
}

function getMember(email, success, fail) {
  api.get(`/user/${email}`).then(success).catch(fail);
}

function join(user, success, fail) {
  api.post(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

function modifyMember(user, success, fail) {
  api.put(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

function withdrawal(email, success, fail) {
  api.delete(`/user/${email}`).then(success).catch(fail);
}

// function logout(success, fail)

export { login, findById, getMember, join, modifyMember, withdrawal };
