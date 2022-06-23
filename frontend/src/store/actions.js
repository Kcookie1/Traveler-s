import {
  FETCH_M_HOTEL_LIST,
  FETCH_M_HOTEL,
  IS_LOGGEDIN,
  NOT_LOGGEDIN,
  SET_USER,
  GET_HOTEL_TYPE,
  FETCH_BM_HOTEL_LIST,
  FETCH_BM_HOTEL

} from "./mutation-types";

import axios from "axios";
import router from "@/router";

export default {
    fetchMHotelList ({ commit }) {
        return axios.get('http://localhost:7777/hotel/mainList')
                .then((res) => {
                    commit(FETCH_M_HOTEL_LIST, res.data)
                })
    },
    fetchMHotel ({ commit }, hotelNo) {
        return axios.get(`http://localhost:7777/hotel/mRead/${hotelNo}`)
                .then((res) => {
                    commit(FETCH_M_HOTEL, res.data)
                })
    },
    fetchBmHotelList ({ commit }) {
        return axios.get('http://localhost:7777/hotel/bm/list')
                .then((res) => {
                    commit(FETCH_BM_HOTEL_LIST, res.data)
                })
    },
    fetchBmHotel ({ commit }, hotelNo) {
        return axios.get(`http://localhost:7777/hotel/bm/${hotelNo}`)
                .then((res) => {
                    commit(FETCH_BM_HOTEL, res.data)
                })
    },
    attemptLogin({commit, state}, payload){
        axios.post("http://localhost:7777/login", payload, {withCredentials: true})
        .then((res) => {
            localStorage.setItem("access_token", res.data.accessToken)
            commit(IS_LOGGEDIN)
            console.log(state.isLoggedIn)
            router.push("/")
        })
        .catch(() => alert("Invalid username or password"))
    },
    attemptLogout({commit}){
        axios.post('http://localhost:7777/logout', {}, {withCredentials: true})
        .then(() => {
            localStorage.removeItem("access_token")
            commit(NOT_LOGGEDIN)
            router.push("/")
        })
        .catch(() => alert("로그인 실패"))
    },
    validate_login({commit}){
        localStorage.getItem('access_token') ? commit(IS_LOGGEDIN) : commit(NOT_LOGGEDIN)
    },
    setUser({commit}){
        axios.get("http://localhost:7777/getUser")
            .then((res => {
                commit(SET_USER, res.data)
            }))
    },
    getHotelType({commit}){
        axios.get("http://localhost:7777/room/getHotelType")
            .then((res => {
                commit(GET_HOTEL_TYPE, res.data)
            }))
    },
    fetchBmHotelList ({ commit }) {
        return axios.get('http://localhost:7777/hotel/bm/list')
                .then((res) => {
                    commit(FETCH_BM_HOTEL_LIST, res.data)
                })
    },
    fetchBmHotel ({ commit }, hotelNo) {
        return axios.get(`http://localhost:7777/hotel/bm/${hotelNo}`)
                .then((res) => {
                    commit(FETCH_BM_HOTEL, res.data)
                })
    },

}