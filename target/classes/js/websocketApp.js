var stompClient = null;

function setConnected() {

    document.getElementById('conversationDiv').style.visibility
        = 'visible';
    document.getElementById('response').innerHTML = '';
}



function connect(ctx) {
    var socket = new SockJS(ctx+'/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function() {
        console.log('Web Socket is connected');
        stompClient.subscribe('/users/queue/messages', function(message) {
            showMessageOutput(message.body);
        });
    });
}

function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage(productId, action) {
    stompClient.send("/app/hello", {}, JSON.stringify({'productId':productId, 'action':action}));
}

function showMessageOutput(message) {
    const cart = document.getElementById('cart')
    var basket = JSON.parse(message);
    var cost = 0;
    cart.innerHTML = ''
    let html = '<caption>Cart</caption><tr><th>Name</th><th>Price</th><th>Count</th><th>Remove</th></tr>'
    for (var i = 0; i < basket.length; i++) {
        cost += basket[i].product.price*basket[i].count;
        html += '<tr><td>' + basket[i].product.name + '</td><td>' + basket[i].product.price + '</td>' + '<td>' + basket[i].count + 'шт.' + '</td>' +
            '<td>' + '<button value="add to cart" type="button" onclick="sendMessage('+basket[i].product.id +','+2+')">Удалить</button> ' + '</td></tr>';
    }

    html += '<tr><td colspan="4" align="center"> Total cost: ' + cost + '</td></tr>';

    cart.innerHTML = html;
}