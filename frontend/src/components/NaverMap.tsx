import React, {useEffect} from "react";
import {observer} from "mobx-react-lite";

export interface Props {
}

const NaverMap = observer((props: Props) => {
    useEffect(() => {
        let map = null;
        const initMap = () => {
            const map = new naver.maps.Map("map", {
                center: new naver.maps.LatLng(37.511337, 127.012084),
                zoom: 13,
            });
        };
        initMap();
    }, []);

    //지도 사이즈 관련 스타일
    const mapStyle = {
        width: "90%",
        height: "600px",
    };

    return (
        <React.Fragment>
            <div id="map" style={mapStyle}/>
        </React.Fragment>
    );
})

export default NaverMap;