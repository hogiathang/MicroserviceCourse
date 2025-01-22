package com.cards.cards.service.impl;

import com.cards.cards.constants.CardsConstants;
import com.cards.cards.dto.CardsDto;
import com.cards.cards.entity.Cards;
import com.cards.cards.exception.CardAlreadyExistException;
import com.cards.cards.exception.ResourceNotFoundException;
import com.cards.cards.mapper.CardsMapper;
import com.cards.cards.repository.CardRepository;
import com.cards.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    CardRepository cardRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistException("Card already exist");
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }


    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Card",
                        "mobileNumber",
                        mobileNumber
                )
        );
        return CardsMapper.CardsToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {

        Cards cards = cardRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Card",
                        "cardNumber",
                        cardsDto.getCardNumber()
                )
        );
        cardRepository.save(CardsMapper.CardsDtoToCards(cardsDto, cards));
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Card Service",
                        "mobileNumber",
                        mobileNumber
                )
        );
        cardRepository.deleteById(cards.getCardId());
        return true;
    }
}
