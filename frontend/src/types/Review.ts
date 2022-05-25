export default interface IReview {
    // grade의 경우, 현재 Place에서 rating field를 제외한 후에 차차 추가하자
    id: string;
    productId: string;
    userId: string;
    title: string;
    content: string;
    grade: number;
}