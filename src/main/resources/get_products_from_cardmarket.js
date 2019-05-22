const getName = () => $(".card-image").find("img").eq(1).attr('alt');

const getDesc = () => $(".info-list-container").children().eq(1).children().eq(1).text();

const getPrice = () => $(".info-list-container").children().eq(0).children().eq(13).children().eq(0).text();

const getImg = () => 'http:' + $(".card-image").find("img").eq(1).attr('src');

const getProduct = () => JSON.stringify({
    name: getName(),
    description: getDesc(),
    price: getPrice(),
    imageUrl: getImg()
})

getProduct()
