import {observer} from "mobx-react-lite";
import IPlace from "../types/Place";

export interface Props {
    place: IPlace
}

const Place = observer((props: Props) => {
    return (
        <div>
            <div>
                <strong>
                    {props.place.name}
                </strong>
                <p>{props.place.description}</p>
            </div>
            <div>
                {props.place.placeType}
                {props.place.address}
                {props.place.rating}
            </div>
        </div>
    )
});

export default Place;