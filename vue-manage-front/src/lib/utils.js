const LOCAL_JWT_KEY = "jskdfls";
export const setJwtToken = (jwtToken) => {
    localStorage.setItem(LOCAL_JWT_KEY,jwtToken)
}
export const getJwtToken = () => {
    return localStorage.getItem(LOCAL_JWT_KEY)
}
export const getTokenUser = () => {
    let userString = decodeURIComponent(
            window.atob(getJwtToken().split('.')[1])
    )
    return JSON.parse(userString).sub
}