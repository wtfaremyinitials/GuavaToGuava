var React = require('react');

var keys = obj => Object.keys(obj);

var BASE_API_URL = '/api';
var get = function(endpoint, params, cb) {
    params = keys(params).reduce((o, n) => o + n + '=' + params[n] + '&' , '?');
    params = params.substr(0, params.length - 1);
    require('got')(BASE_API_URL + endpoint + params, (err, data) => cb(err, JSON.parse(data)));
};


var repeat = function(cb, interval) {
    cb();
    setInterval(cb, interval);
};

var answers;   require('got')('/answers.json', (err, data) => answers = JSON.parse(data));
var questions; require('got')('/questions.json', (err, data) => answers = JSON.parse(data));

// ref.setState({ gid: 1, pid: 0, players: [{ name: 'Will', score: 4, czar: true }, { name: 'Brady', score: 3, czar: false}], hand: [0, 1, 2]  })

class GuavasToGuavas extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            players: [],
            hand: [],
            pid: null,
            gid: null
        };
    }

    componentDidMount() {
        window.ref = this; 
        repeat(() => {
            // console.log(this.state);
            if(!this.state.gid)
                return;
            // get('/games/' + this.state.gid + '/status', { pid: this.state.pid }, (err, status) => this.setState(status));
        }, 500);
    }

    render() {
        return this.state.gid ? this.renderGame() : this.renderMenu();
    }

    renderGame() {
        return (
            <div className={ (this.state.players[this.state.pid] || { czar : false }).czar ? 'isczar' : 'isplayer' }>
                <Scoreboard players={ this.state.players } />
                <br />
                <Hand hand={ this.state.hand } chooseCard={ card => this.handleChooseCard(card) } />
            </div>
        );
    }

    renderMenu() {
        return (
            <div>
                <h1 className="home-buttons" onClick={ () => this.handleJoinGame()   }>Join Game</h1>
                <h1 className="home-buttons" onClick={ () => this.handleCreateGame() }>Create Game</h1>
            </div>
        );
    }
    
    handleJoinGame() {
        this.joinGame(prompt('Enter the game ID'), prompt('Enter your name'));
    }
    
    handleCreateGame() {
        get('/games/create', {}, (err, gid) => this.joinGame(gid, prompt('Enter your name')));
    }
    
    joinGame(gid, name) {
        get('/games/' + gid + '/join', { name }, (err, pid) => this.setState({ gid, pid }));
    }
    
    handleChooseCard(cid) {
        this.setState({ hand: [] });
        get('/games/' + this.state.gid + '/choose', { cid, pid: this.state.pid }, () => {});
    }
    
}

class Scoreboard extends React.Component {
    
    constructor(props) {
        super(props);
    }
    
    render() {
        return (
            <ul>
                { this.props.players.map(this.renderPlayer) }
            </ul>  
        );
    }
    
    renderPlayer(player) {
        return <li className={ player.czar ? 'czar' : '' } key={ player.name }>{ player.name } - { player.score }</li>;
    }
    
}

class Hand extends React.Component {
    
    constructor(props) {
        super(props);
    }
    
    render() {
        return (
            <ul>
                { this.props.hand.map((hand) => this.renderCard(hand)) }
            </ul>
        );
    }
    
    renderCard(cid) {
        return <li key={ answers[cid] } onClick={ () => this.props.chooseCard(cid) }>{ answers[cid] }</li>;    
    }   
     
}

React.render(<GuavasToGuavas/>, document.getElementById("game"));
