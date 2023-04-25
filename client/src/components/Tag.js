import styled from "styled-components";
import { useState } from 'react'

function Tag({onFocus}) {
    const [tags, setTags] = useState([]);
    const [tag, setTag] = useState("");
    
    const removeTag = (i) => {
      const clonetags = tags.slice();
      clonetags.splice(i, 1);
      setTags(clonetags);
    };

    const addTag = (e) => {
      setTag(e.target.value);
    };

    const handleKeyPress = (e) => {
      if (e.key === "Enter") {
        handleClick();
      }
    };
    
    const handleClick = () => {
      setTags([...tags, tag]);
      setTag("");
    };
    
    return (
        <TagContainer onFocus={onFocus}>
          {tags.map((e, i) => (
            <Hash key={i}>
              <HashName>{e}</HashName>
              <HashBtn onClick={() => removeTag(i)}><span class="material-symbols-outlined">close</span></HashBtn>
            </Hash>
          ))}
  
          <InputBox
            placeholder={tags.length === 0 ? "e.g. (asp.net-mvc objective-c ruby-on-rails)" : ""}
            onChange={(e) => addTag(e)}
            onKeyPress={(e) => handleKeyPress(e)}
            value={tag}
          />
        </TagContainer>
    );
}

export default Tag;

const TagContainer = styled.div`
    display: flex;
    align-items: center;
    height: 33px;
    font-size: 13px;
    border: 1px solid hsl(210,8%,75%);
    border-radius: 3px;
    padding: 8px 9px;

    &:focus-within {
        box-shadow: 0 0 0 4px rgba(0, 149, 255, .15);
        border: 1px solid #59a4de;
        outline: none;
    }
`;
const Hash = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 3px;
    padding: 0 4px;
    margin: 2px;
    height: 24.15px;
    color: hsl(205,47%,42%);
    background-color: hsl(205,46%,92%);
`;
const HashName = styled.h3`
    font-size: 12px;
    font-weight: 400;
`;
const HashBtn = styled.button`
    border: none;
    cursor: pointer;
    background-color: hsl(205,46%,92%);
    color: hsl(205,47%,42%);
    margin: 0 0 0 4px;
    padding: 1px;
    display: flex;

    .material-symbols-outlined {
        font-size: 15px;
        font-weight: 600;
        border-radius: 3px;
    }

    .material-symbols-outlined:hover {
        background-color: hsl(205,46%,32%);
        color: hsl(205,46%,92%);
    }
`;
const InputBox = styled.input`
    border: none;
    width: 100%;

    &:focus {
    outline: none;
  }
`;