import styled from "styled-components";

const DiscardModal = ({open, close, onDiscard}) => {

    return (
        open && <DiscardModalContainer className={open ? "openModal" : ""}>
                <DiscardModalBox>
                    <h1>Discard question</h1>
                    <p>Are you sure you want to discard this question? This cannot be undone.</p>
                    <div className="discard-btn-container">
                        <DiscardButton 
                            onClick={() => { 
                                onDiscard(); 
                                close();
                            }}
                        >
                            Discard question
                        </DiscardButton>
                        <CancelButton onClick={close}>Cancel</CancelButton>
                    </div>
                    <span class="material-symbols-outlined" onClick={close}>close</span>
                </DiscardModalBox>    
        </DiscardModalContainer>
    )
}

export default DiscardModal;


export const DiscardModalContainer = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 1000;
    display: flex;
    justify-content: center;
    align-items: center;

    .openModal {
        display: flex;
        align-items: center;
        justify-content: center;
    }
`;


export const DiscardModalBox = styled.div`
    position: relative;
    width: 483px;
    height: 174px;
    padding: 24px;
    background-color: white;
    border-radius: 7px;
    box-shadow: 0 1px 4px hsla(0,0%,0%,0.09), 0 3px 8px hsla(0, 0%, 0%, 0.09), 0 4px 13px hsla(0, 0%, 0%, 0.13);

    h1 {
        font-size: 27px;
        font-weight: 500;
        margin: 0 0 16px;
        color: #c22e32;
    }

    p {
        width: 100%;
        font-size: 13px;
        margin: 0 0 24px;
        color: hsl(210,8%,25%);
    }

    .material-symbols-outlined {
        position: absolute;
        right: 12px; 
        top: 12px;
        font-size: 14px;
        font-weight: 600;
        padding: 12px;
        color: hsl(210,8%,45%);

        &:hover, &:focus {
            color: hsl(210,8%,35%);
            background-color: hsl(210,8%,97.5%);
            cursor: pointer;
        }

        &:focus {
            box-shadow: 0 0 0 4px rgba(131, 140, 149, .15);
        }

        &:active {
            background-color: hsl(210,8%,95%);
            box-shadow: none;
        }
    }
`;

export const DiscardButton = styled.button`
    background-color: hsl(358, 62%, 52%);
    border: 1px solid transparent;
    border-radius: 3px;
    box-shadow: rgba(255, 255, 255, .4) 0 1px 0 0 inset;
    box-sizing: border-box;
    color: #fff;
    cursor: pointer;
    display: inline-block;
    font-family: -apple-system,system-ui,"Segoe UI","Liberation Sans",sans-serif;
    font-size: 13px;
    font-weight: 500;
    line-height: 1.15385;
    outline: none;
    padding: 8px .8em;
    position: relative;
    text-align: center;
    text-decoration: none;
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
    vertical-align: baseline;
    white-space: nowrap;
    width: 124px;
    height: 38px;
    margin-right: 4px;

    &:hover {
        background-color: #C22E32;
    }

    &:focus {
        background-color: #C22E32;
        box-shadow: 0 0 0 4px rgba(208, 57, 62, .15);
    }

    &:active {
        background-color: #932123;
        box-shadow: none;
    }

`;

export const CancelButton = styled.button`
    background-color: transparent;
    border: none;
    border-radius: 3px;
    box-shadow: rgba(255, 255, 255, .4) 0 1px 0 0 inset;
    box-sizing: border-box;
    color: hsl(210,8%,45%);
    cursor: pointer;
    display: inline-block;
    font-family: -apple-system,system-ui,"Segoe UI","Liberation Sans",sans-serif;
    font-size: 13px;
    font-weight: 400;
    line-height: 1.15385;
    margin: 0 4px;
    outline: none;
    padding: 8px .8em;
    position: relative;
    text-align: center;
    text-decoration: none;
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
    vertical-align: baseline;
    white-space: nowrap;
    width: 64px;
    height: 38px;

    &:hover, &:focus {
        color: hsl(210,8%,35%);
        background-color: hsl(210,8%,97.5%);
    }

    &:focus {
        box-shadow: 0 0 0 4px rgba(131, 140, 149, .15);
    }

    &:active {
    background-color: hsl(210,8%,95%);
    box-shadow: none;
    }
`;