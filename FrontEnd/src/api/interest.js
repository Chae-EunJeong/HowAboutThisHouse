import { apiInstance, realtorInstance } from "./index.js";

const api = apiInstance();
const realtor = realtorInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function getInterestInfo(dongCode, success, fail) {
  api.get(`/interest/info/${dongCode}`).then(success).catch(fail);
}

function registerInterest(interest, success, fail) {
  api.post(`/interest`, JSON.stringify(interest)).then(success).catch(fail);
}
function getInterestList(email, success, fail) {
  api.get(`/interest/${email}`).then(success).catch(fail);
}

function deleteInterest(interestNo, success, fail) {
  api.delete(`/interest/${interestNo}`).then(success).catch(fail);
}

function realtorList(params, success, fail) {
  realtor.get(`${params}`).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  dongList,
  getInterestInfo,
  registerInterest,
  getInterestList,
  deleteInterest,
  realtorList,
};
